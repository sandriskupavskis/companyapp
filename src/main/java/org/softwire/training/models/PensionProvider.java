package org.softwire.training.models;

import javax.persistence.*;

@Entity
@Table(name = "pension_providers")
public class PensionProvider {

    @Id
    @Column(name = "pension_provider_name", nullable = false)
    private String pension_provider_name;

    public PensionProvider() {
    }

    public PensionProvider(String pension_provider_name) {
        this.pension_provider_name = pension_provider_name;
    }

    public String getPension_provider_name() {
        return pension_provider_name;
    }

    public void setPension_provider_name(String pension_provider_name) {
        this.pension_provider_name = pension_provider_name;
    }
}
