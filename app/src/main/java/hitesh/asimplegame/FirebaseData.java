package hitesh.asimplegame;

public class FirebaseData {
    private String userID;
    private String userName;
    private String userScore;

    public FirebaseData() {
        this.userID = "";
        this.userName = "";
        this.userScore = "";
    }

    public FirebaseData(String id, String name, String score) {
        this.userID = id;
        this.userName = name;
        this.userScore = score;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserScore() {
        return userScore;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserScore(String userScore) {
        this.userScore = userScore;
    }

}
