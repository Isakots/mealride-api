package hu.student.projlab.mealride_api.restaurant;

public class RestaurantForm {
    private String name;
    private String avgdeliverytime;
    private Short minorderprice;
    private Short deliveryprice;
    private String openingtime;
    private String closingtime;

    public RestaurantForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvgdeliverytime() {
        return avgdeliverytime;
    }

    public void setAvgdeliverytime(String avgdeliverytime) {
        this.avgdeliverytime = avgdeliverytime;
    }

    public Short getMinorderprice() {
        return minorderprice;
    }

    public void setMinorderprice(Short minorderprice) {
        this.minorderprice = minorderprice;
    }

    public Short getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(Short deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    public String getOpeningtime() {
        return openingtime;
    }

    public void setOpeningtime(String openingtime) {
        this.openingtime = openingtime;
    }

    public String getClosingtime() {
        return closingtime;
    }

    public void setClosingtime(String closingtime) {
        this.closingtime = closingtime;
    }
}
