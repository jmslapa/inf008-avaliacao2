package domain.unit.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import domain.unit.contracts.Asset;
import domain.terrain.models.aggregate.Position;

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
     * Mapa de acessórios equipados às unidades, onde a chave é o nome relativo da classe.
     */
    private Map<String, Asset<?>> assets;

    /**
     * Construtor padrão da classe Unit
     * 
     * @param id caso utilize auto incremento na camada de persistência, deve receber null
     * @param assets deve-se passar um ou mais parâmetros de objetos 
     *               que implementem a interface domain.units.contracts.Asset
     * 
     * @return Self
     */
    public Unit(Long id, Asset<?>... assets) {
        this.id = id;
        this.position = null;
        setAssets(assets);
    }    
    
    public Long getId() {
        return this.id;
    }

    public Position getPosition() {
        return this.position;
    }
    
    private Asset<?> getAsset(Class<?> objectClass) {
        return this.assets.get(objectClass.getName());
    }

    public Unit setId(Long id) {
        this.id = id;
        return this;
    }

    public Unit setPosition(Position position) {
        this.position = position;
        return this;
    }

    public Boolean hasAsset(Class<?> objectClass) {
        return this.assets.containsKey(objectClass.getName()) ? true : false;
    }

    public Boolean isPositioned() {
        return this.position != null ? true : false;
    }
    
    public Boolean hasAsset(Asset<?> asset) {
        return this.assets.containsValue(asset) ? true : false;
    }

    public <T> T useAsset(Class<?> objectClass, Class<T> returnType) throws Exception {
        if(!this.isPositioned())
            throw new Exception("A unidade não está posicionada");
        if(!this.hasAsset(objectClass))
            throw new Exception("A unidade não possui o asset " + objectClass.getName());
        return returnType.cast(this.getAsset(objectClass).scan(this.position));
    }

    public void removeAsset(Class<?> objectClass) {
        this.assets.remove(objectClass.getName());
    }

    /**
     * Inicializa o atributo assets e registra os objetos possivelmente recebidos por parâmetro.
     * Caso já exista uma instância do asset passado, o mesmo será substituído, caso não, será incluído.
     * @param assets Array de objetos que mplementam a interface domain.units.contracts.Asset
     */
    public Unit setAssets(Asset<?> ...assets) {
        this.assets = new HashMap<String, Asset<?>>();
        for(Asset<?> asset : assets) {
            String className = asset.getClass().getName();
            if(this.assets.containsKey(className)) {
                this.assets.replace(className, asset);
            } else {
                this.assets.put(className, asset);
            }
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Unit)) {
            return false;
        }
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id) && Objects.equals(position, unit.position) && Objects.equals(assets, unit.assets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, assets);
    }

}
