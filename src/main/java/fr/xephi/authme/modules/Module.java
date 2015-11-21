package fr.xephi.authme.modules;

/**
 */
public abstract class Module {

    /**
     */
    enum ModuleType {
        MANAGER,
        MYSQL,
        REDIS,
        ACTIONS,
        CONVERTERS,
        EMAILS,
        CUSTOM
    }

    /**
     * Method getName.
     * @return String
     */
    public abstract String getName();

    /**
     * Method getType.
     * @return ModuleType
     */
    public abstract ModuleType getType();

    public void load() {
    }

    public void unload() {
    }
}
