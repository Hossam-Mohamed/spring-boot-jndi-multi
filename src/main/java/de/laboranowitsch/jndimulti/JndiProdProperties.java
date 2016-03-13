package de.laboranowitsch.jndimulti;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Assign JNDI naming components from properties.
 *
 * Created by cla on 3/12/16.
 */
@Component
@Profile({Profiles.PROD})
public class JndiProdProperties {

    @Value("${de.laboranowitsch.jndi.datasource.one}")
    private String jndiStringOne;
    @Value("${de.laboranowitsch.jndi.datasource.two}")
    private String jndiStringTwo;

    public String getJndiStringOne() {
        return jndiStringOne;
    }

    public String getJndiStringTwo() {
        return jndiStringTwo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JndiProdProperties{");
        sb.append("jndiStringOne='").append(jndiStringOne).append('\'');
        sb.append(", jndiStringTwo='").append(jndiStringTwo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
