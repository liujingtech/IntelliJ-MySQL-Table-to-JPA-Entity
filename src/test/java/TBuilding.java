import javax.persistence.*;
import java.util.Date;

/**
 * 楼盘主表
 */
@Entity
@Table(name = "t_building")
public class TBuilding {

    /**
     * 主键 ID
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id;

    /**
     * 名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 销售状态 1-在售， 2-待售， 3-售罄
     */
    @Column(name = "sale_status")
    private Integer saleStatus;

    /**
     * 占地面积:单位平方分米
     */
    @Column(name = "floor_space")
    private Long floorSpace;

    /**
     * 排序 999-普通排序，1,2,....10(数字越小越靠前)
     */
    @Column(name = "building_sort")
    private Integer buildingSort;

    /**
     * 发证时间
     */
    @Column(name = "card_date")
    private Date cardDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalestatus() {
        return saleStatus;
    }

    public void setSalestatus(Integer salestatus) {
        this.saleStatus = salestatus;
    }

    public Long getFloorspace() {
        return floorSpace;
    }

    public void setFloorspace(Long floorspace) {
        this.floorSpace = floorspace;
    }

    public Integer getBuildingsort() {
        return buildingSort;
    }

    public void setBuildingsort(Integer buildingsort) {
        this.buildingSort = buildingsort;
    }

    public Date getCarddate() {
        return cardDate;
    }

    public void setCarddate(Date carddate) {
        this.cardDate = carddate;
    }

    public TBuilding() {
        this.id = null;
        this.name = null;
        this.saleStatus = 0;
        this.floorSpace = 0L;
        this.buildingSort = 999;
        this.cardDate = new Date();
    }
}