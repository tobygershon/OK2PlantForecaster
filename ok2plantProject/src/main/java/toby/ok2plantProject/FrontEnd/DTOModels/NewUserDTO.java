package toby.ok2plantProject.FrontEnd.DTOModels;

public class NewUserDTO {
    private String email;
    private String ok2PlantDate;
    private boolean wantsFollowUp;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOk2PlantDate() {
        return ok2PlantDate;
    }

    public void setOk2PlantDate(String ok2PlantDate) {
        this.ok2PlantDate = ok2PlantDate;
    }

    public boolean isWantsFollowUp() {
        return wantsFollowUp;
    }

    public void setWantsFollowUp(boolean wantsFollowUp) {
        this.wantsFollowUp = wantsFollowUp;
    }
}
