package org.softwire.training.models;

import javax.persistence.*;

@Entity
@Table(name = "pension_funds")
public class PensionFund {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "employee_id", nullable = false)
    private int employee_id;

    @Column(name = "amount_contributed", nullable = false)
    private int amount_contributed;

    @Column(name = "pension_provider", nullable = false)
    private String pension_provider;

    public PensionFund() {
    }

    public PensionFund(int employee_id, int amount_contributed, String pension_provider) {
        this.employee_id = employee_id;
        this.amount_contributed = amount_contributed;
        this.pension_provider = pension_provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getAmount_contributed() {
        return amount_contributed;
    }

    public void setAmount_contributed(int amount_contributed) {
        this.amount_contributed = amount_contributed;
    }

    public String getPension_provider() {
        return pension_provider;
    }

    public void setPension_provider(String pension_provider) {
        this.pension_provider = pension_provider;
    }
}
