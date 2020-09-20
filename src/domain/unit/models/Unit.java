package domain.unit.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import domain.terrain.models.aggregate.Position;
import domain.unit.contracts.Asset;
import domain.unit.contracts.Mobile;
import support.utils.MeasureResponse;

abstract public class Unit {

    /**
     * Identificador único da unidade
     */
    private Long id;

    /**
     * Posição da unidade no terreno monitorado
     */
    private Position position;

    /**
     * Mapa de acessórios equipados às unidades, onde a chave é o nome relativo da
     * classe.
     */
    private Map<String, Asset> assets;

    /**
     * Construtor padrão da classe Unit
     * 
     * @param id     caso utilize auto incremento na camada de persistência, deve
     *               receber null
     * @param assets deve-se passar um ou mais parâmetros de objetos que implementem
     *               a interface domain.units.contracts.Asset
     * 
     * @return Self
     */
    public Unit(Long id, Asset... assets) {
        this.id = id;
        this.position = null;
        setAssets(assets);
    }

    public Long getId() {
        return this.id;
    }

    public Unit setId(Long id) {
        this.id = id;
        return this;
    }

    public Position getPosition() {
        return this.position;
    }

    public Unit setPosition(Position position) {
        this.position = position;
        return this;
    }

    private Asset getAsset(Class<?> objectClass) {
        return this.assets.get(objectClass.getName());
    }

    /**
     * Inicializa o atributo assets e registra os objetos possivelmente recebidos
     * por parâmetro. Caso já exista uma instância do asset passado, o mesmo será
     * substituído, caso não, será incluído.
     * 
     * @param assets Array de objetos que mplementam a interface
     *               domain.units.contracts.Asset
     */
    public Unit setAssets(Asset... assets) {
        this.assets = new HashMap<String, Asset>();
        for (Asset asset : assets) {
            String className = asset.getClass().getName();
            if (this.assets.containsKey(className)) {
                this.assets.replace(className, asset);
            } else {
                this.assets.put(className, asset);
            }
        }
        return this;
    }

    public Boolean isPositioned() {
        return this.position != null ? true : false;
    }

    public Boolean hasAsset(Class<?> objectClass) {
        return this.assets.containsKey(objectClass.getName()) ? true : false;
    }

    public Boolean hasAsset(Asset asset) {
        return this.assets.containsValue(asset) ? true : false;
    }

    public Boolean hasAssets(List<Class<?>> objectClasses) {
        for (Class<?> objectClass : objectClasses) {
            if (!this.hasAsset(objectClass))
                return false;
        }
        return true;
    }

    public MeasureResponse<?> useAsset(Class<?> objectClass) throws Exception {
        
        if (!this.isPositioned())
            throw new Exception("A unidade não está posicionada");
        if (!this.hasAsset(objectClass))
            throw new Exception("A unidade não possui o asset " + objectClass.getName());

        return this.getAsset(objectClass)
                   .scan(this.position)
                   .setAgent(this)
                   .setTarget(this.position);
    }

    public void removeAsset(Class<?> objectClass) {
        this.assets.remove(objectClass.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Unit)) {
            return false;
        }
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id) && Objects.equals(position, unit.position)
                && Objects.equals(assets, unit.assets);
    }

    public static Unit getClosest(List<Unit> units, Position target) {

        Comparator<Unit> distanceComparator = new Comparator<Unit>() {
            public int compare(Unit unit1, Unit unit2) {
                try {
                    Double dUnit1 = ((Mobile) unit1).getDistance(target);
                    Double dUnit2 = ((Mobile) unit2).getDistance(target);
                    if(dUnit1 < dUnit2) {
                        return -1;
                    } else if(dUnit1 > dUnit2) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    return 1;
                }
            }
        };       

        return Collections.min(units, distanceComparator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, assets);
    }

}
