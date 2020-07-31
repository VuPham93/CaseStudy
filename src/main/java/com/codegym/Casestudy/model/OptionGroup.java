package com.codegym.Casestudy.model;

import javax.persistence.*;

@Entity
@Table(name = "option_group")
public class OptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionGroupId;

    @Column(unique = true)
    private String optionGroupName;

    public OptionGroup(Long optionGroupId, String optionGroupName) {
        this.optionGroupId = optionGroupId;
        this.optionGroupName = optionGroupName;
    }

    public OptionGroup() {
    }

    public Long getOptionGroupId() {
        return optionGroupId;
    }

    public void setOptionGroupId(Long optionGroupId) {
        this.optionGroupId = optionGroupId;
    }

    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }
}
