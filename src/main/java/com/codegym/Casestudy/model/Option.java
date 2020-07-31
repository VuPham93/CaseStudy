package com.codegym.Casestudy.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "optionGroupId")
    private OptionGroup optionGroup;

    @NotEmpty
    private String optionName;

    public Option(Long optionId, OptionGroup optionGroup, @NotEmpty String optionName) {
        this.optionId = optionId;
        this.optionGroup = optionGroup;
        this.optionName = optionName;
    }

    public Option() {
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public OptionGroup getOptionGroup() {
        return optionGroup;
    }

    public void setOptionGroup(OptionGroup optionGroup) {
        this.optionGroup = optionGroup;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
