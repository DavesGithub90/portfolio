package name.abuchen.portfolio.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * A set of configurations used to manage charts and column setup
 */
public class ConfigurationSet
{
    public enum WellKnownConfigurationSets
    {
        /**
         * custom client filter configurations; data contains comma separated
         * list of account/portfolio UUIDs
         */
        CLIENT_FILTER_DEFINITIONS("client-filter-definitions"), //$NON-NLS-1$
        /** selected client filter in views; view name is key */
        CLIENT_FILTER_SELECTION("client-filter-selection"); //$NON-NLS-1$

        private WellKnownConfigurationSets(String key)
        {
            this.key = key;
        }

        private String key;

        public String getKey()
        {
            return key;
        }
    }

    /**
     * A configuration has a UUID, a name given by the user, and a data string.
     */
    public static class Configuration
    {
        private String uuid;
        private String name;
        private String data;

        public Configuration()
        {
            // used for xml deserialisation
        }

        /* protobuf only */ Configuration(String uuid)
        {
            this.uuid = uuid;
        }

        public Configuration(String name, String data)
        {
            this(UUID.randomUUID().toString(), name, data);
        }

        public Configuration(String uuid, String name, String data)
        {
            this.uuid = uuid;
            this.name = name;
            this.data = data;
        }

        public String getUUID()
        {
            return uuid;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getData()
        {
            return data;
        }

        public void setData(String data)
        {
            this.data = data;
        }
    }

    private List<Configuration> configurations = new ArrayList<>();

    public Stream<Configuration> getConfigurations()
    {
        return configurations.stream();
    }

    /**
     * Returns the index of the specified configuration, or -1 if the
     * configuration set does not contain this configuration.
     */
    public int indexOf(Configuration config)
    {
        return configurations.indexOf(config);
    }

    /**
     * Returns the configuration with the given UUID.
     */
    public Optional<Configuration> lookup(String uuid)
    {
        if (uuid == null)
            return Optional.empty();
        return configurations.stream().filter(c -> uuid.equals(c.getUUID())).findAny();
    }

    /**
     * Adds a configuration to the set.
     */
    public void add(Configuration configuration)
    {
        configurations.add(configuration);
    }

    /**
     * Inserts the specified configuration at the specified location.
     */
    public void add(int index, Configuration configuration)
    {
        configurations.add(index, configuration);
    }

    /**
     * Removes the given configuration from the set.
     */
    public void remove(Configuration configuration)
    {
        configurations.remove(configuration);
    }

    /**
     * Removes all configurations from the set.
     */
    public void clear()
    {
        configurations.clear();
    }
}
