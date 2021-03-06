package com.lynx.fqb.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = { "id", "number" }, callSuper = false)
@ToString(of = { "id", "number" })
@Entity
public class SellOrder extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;

    @OneToMany(mappedBy = "sellOrder", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    private Date dueDate;

    private BigDecimal total;

    @ManyToOne
    private User creator;

    @ManyToOne
    private User supervisor;

    public void addItem(Item item) {
        items.add(item);
        item.setSellOrder(this);
    }
}
