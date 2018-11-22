import javax.persistence.*;
import java.util.Date;

/**
 * 楼盘主表
 */
@Entity
@Table(name = "t_zibo_building")
public class TBuilding {

    /**
     * 楼盘ID
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private Long id;

    /**
     * 外部id(局方楼盘ID)
     */
    @Column(name = "external_id")
    private String externalId;

    /**
     * 楼盘名称（局方同步）
     */
    @Column(name = "name")
    private String name;

    /**
     * 参考价格类型,  1：均价, 2：单价-起， 3：总价-起， 4：售价待定
     */
    @Column(name = "reference_type")
    private Integer referenceType;

    /**
     * 参考价格，单位分
     */
    @Column(name = "reference_price")
    private Long referencePrice;

    /**
     * 均价，根据参考价类型和公式计算得出,默认值为0
     */
    @Column(name = "average_price")
    private Long averagePrice;

    /**
     * 物业类型，具体有0-住宅、1-别墅、2-商用、3-酒店式公寓，此处需要拼接
     */
    @Column(name = "property_type")
    private String propertyType;

    /**
     * 开发商（局方同步）
     */
    @Column(name = "developer")
    private String developer;

    /**
     * 楼盘区域id（局方同步-字典表取值，G端不可修改）
     */
    @Column(name = "district_id")
    private Long districtId;

    /**
     * 楼盘地址（局方同步，第一次同步后，由我们自己系统维护）
     */
    @Column(name = "address")
    private String address;

    /**
     * 经纬度,逗号分开
     */
    @Column(name = "latitude_longitude")
    private String latitudeLongitude;

    /**
     * 销售状态 1-在售， 2-待售， 3-售罄
     */
    @Column(name = "sale_status")
    private Integer saleStatus;

    /**
     * 开盘时间
     */
    @Column(name = "building_open_time")
    private String buildingOpenTime;

    /**
     * 交房时间
     */
    @Column(name = "delivery_time")
    private String deliveryTime;

    /**
     * 售楼电话
     */
    @Column(name = "sale_office_mobile")
    private String saleOfficeMobile;

    /**
     * 售楼地址
     */
    @Column(name = "sale_office_address")
    private String saleOfficeAddress;

    /**
     * 建筑类型 0-高层、1-小高层、2-多层，此处需要拼接
     */
    @Column(name = "building_type")
    private String buildingType;

    /**
     * 产权年限,以json格式存储
     */
    @Column(name = "property_year_limit")
    private String propertyYearLimit;

    /**
     * 容积率,以json格式存储
     */
    @Column(name = "volumetric_rate")
    private String volumetricRate;

    /**
     * 占地面积:单位平方分米
     */
    @Column(name = "floor_space")
    private Long floorSpace;

    /**
     * 建筑面积:单位平方分米(局方同步)
     */
    @Column(name = "building_space")
    private Long buildingSpace;

    /**
     * 发证时间(局方同步)
     */
    @Column(name = "card_date")
    private Date cardDate;

    /**
     * 绿化率(保存时乘以1000 显示时除以1000)
     */
    @Column(name = "gerrning_rate")
    private Integer gerrningRate;

    /**
     * 工程进度  0-在建中，待补充
     */
    @Column(name = "project_process")
    private Integer projectProcess;

    /**
     * 物业公司
     */
    @Column(name = "property_company")
    private String propertyCompany;

    /**
     * 车位数
     */
    @Column(name = "car_place_num")
    private Integer carPlaceNum;

    /**
     * 车位率：例如：1：2
     */
    @Column(name = "car_place_rate")
    private String carPlaceRate;

    /**
     * 楼盘标签(多选，逗号隔开)
     */
    @Column(name = "tags")
    private String tags;

    /**
     * 排序 999-普通排序，1,2,....10(数字越小越靠前)
     */
    @Column(name = "building_sort")
    private Integer buildingSort;

    /**
     * 状态：0-下架 1-上架，
     */
    @Column(name = "up_status")
    private Integer upStatus;

    /**
     * 楼盘点击量(点击量先记录redis,根据规则更新)
     */
    @Column(name = "click_count")
    private Integer clickCount;

    /**
     * 是否删除 0-否  1-是
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 最后修改人
     */
    @Column(name = "update_by")
    private Long updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalid() {
        return externalId;
    }

    public void setExternalid(String externalid) {
        this.externalId = externalid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReferencetype() {
        return referenceType;
    }

    public void setReferencetype(Integer referencetype) {
        this.referenceType = referencetype;
    }

    public Long getReferenceprice() {
        return referencePrice;
    }

    public void setReferenceprice(Long referenceprice) {
        this.referencePrice = referenceprice;
    }

    public Long getAverageprice() {
        return averagePrice;
    }

    public void setAverageprice(Long averageprice) {
        this.averagePrice = averageprice;
    }

    public String getPropertytype() {
        return propertyType;
    }

    public void setPropertytype(String propertytype) {
        this.propertyType = propertytype;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Long getDistrictid() {
        return districtId;
    }

    public void setDistrictid(Long districtid) {
        this.districtId = districtid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitudelongitude() {
        return latitudeLongitude;
    }

    public void setLatitudelongitude(String latitudelongitude) {
        this.latitudeLongitude = latitudelongitude;
    }

    public Integer getSalestatus() {
        return saleStatus;
    }

    public void setSalestatus(Integer salestatus) {
        this.saleStatus = salestatus;
    }

    public String getBuildingopentime() {
        return buildingOpenTime;
    }

    public void setBuildingopentime(String buildingopentime) {
        this.buildingOpenTime = buildingopentime;
    }

    public String getDeliverytime() {
        return deliveryTime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliveryTime = deliverytime;
    }

    public String getSaleofficemobile() {
        return saleOfficeMobile;
    }

    public void setSaleofficemobile(String saleofficemobile) {
        this.saleOfficeMobile = saleofficemobile;
    }

    public String getSaleofficeaddress() {
        return saleOfficeAddress;
    }

    public void setSaleofficeaddress(String saleofficeaddress) {
        this.saleOfficeAddress = saleofficeaddress;
    }

    public String getBuildingtype() {
        return buildingType;
    }

    public void setBuildingtype(String buildingtype) {
        this.buildingType = buildingtype;
    }

    public String getPropertyyearlimit() {
        return propertyYearLimit;
    }

    public void setPropertyyearlimit(String propertyyearlimit) {
        this.propertyYearLimit = propertyyearlimit;
    }

    public String getVolumetricrate() {
        return volumetricRate;
    }

    public void setVolumetricrate(String volumetricrate) {
        this.volumetricRate = volumetricrate;
    }

    public Long getFloorspace() {
        return floorSpace;
    }

    public void setFloorspace(Long floorspace) {
        this.floorSpace = floorspace;
    }

    public Long getBuildingspace() {
        return buildingSpace;
    }

    public void setBuildingspace(Long buildingspace) {
        this.buildingSpace = buildingspace;
    }

    public Date getCarddate() {
        return cardDate;
    }

    public void setCarddate(Date carddate) {
        this.cardDate = carddate;
    }

    public Integer getGerrningrate() {
        return gerrningRate;
    }

    public void setGerrningrate(Integer gerrningrate) {
        this.gerrningRate = gerrningrate;
    }

    public Integer getProjectprocess() {
        return projectProcess;
    }

    public void setProjectprocess(Integer projectprocess) {
        this.projectProcess = projectprocess;
    }

    public String getPropertycompany() {
        return propertyCompany;
    }

    public void setPropertycompany(String propertycompany) {
        this.propertyCompany = propertycompany;
    }

    public Integer getCarplacenum() {
        return carPlaceNum;
    }

    public void setCarplacenum(Integer carplacenum) {
        this.carPlaceNum = carplacenum;
    }

    public String getCarplacerate() {
        return carPlaceRate;
    }

    public void setCarplacerate(String carplacerate) {
        this.carPlaceRate = carplacerate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getBuildingsort() {
        return buildingSort;
    }

    public void setBuildingsort(Integer buildingsort) {
        this.buildingSort = buildingsort;
    }

    public Integer getUpstatus() {
        return upStatus;
    }

    public void setUpstatus(Integer upstatus) {
        this.upStatus = upstatus;
    }

    public Integer getClickcount() {
        return clickCount;
    }

    public void setClickcount(Integer clickcount) {
        this.clickCount = clickcount;
    }

    public Integer getIsdeleted() {
        return isDeleted;
    }

    public void setIsdeleted(Integer isdeleted) {
        this.isDeleted = isdeleted;
    }

    public Date getCreatetime() {
        return createTime;
    }

    public void setCreatetime(Date createtime) {
        this.createTime = createtime;
    }

    public Long getCreateby() {
        return createBy;
    }

    public void setCreateby(Long createby) {
        this.createBy = createby;
    }

    public Date getUpdatetime() {
        return updateTime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updateTime = updatetime;
    }

    public Long getUpdateby() {
        return updateBy;
    }

    public void setUpdateby(Long updateby) {
        this.updateBy = updateby;
    }

    public TBuilding() {

        this.id = null;
        this.externalId = null;
        this.name = "";
        this.referenceType = 0;
        this.referencePrice = 0L;
        this.averagePrice = 0L;
        this.propertyType = "";
        this.developer = "";
        this.districtId = 0L;
        this.address = "";
        this.latitudeLongitude = "";
        this.saleStatus = 0;
        this.buildingOpenTime = "";
        this.deliveryTime = "";
        this.saleOfficeMobile = null;
        this.saleOfficeAddress = null;
        this.buildingType = "";
        this.propertyYearLimit = "";
        this.volumetricRate = "";
        this.floorSpace = 0L;
        this.buildingSpace = 0L;
        this.cardDate = new java.util.Date();
        this.gerrningRate = 0;
        this.projectProcess = 0;
        this.propertyCompany = "";
        this.carPlaceNum = 0;
        this.carPlaceRate = "";
        this.tags = "";
        this.buildingSort = 999;
        this.upStatus = 1;
        this.clickCount = 0;
        this.isDeleted = 0;
        this.createTime = new java.util.Date();
        this.createBy = 0L;
        this.updateTime = new java.util.Date();
        this.updateBy = 0L;
    }
}