package custom;

public class Result {

    private double x, y, r;
    private String currentTime;
    private String executionTime;
    private boolean belongsS;

    public Result(double x, double y, double r, String currentTime, String executionTime, boolean belongsS) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.currentTime = currentTime;
        this.executionTime = executionTime;
        this.belongsS = belongsS;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public boolean isBelongsS() {
        return belongsS;
    }

    public void setBelongsS(boolean belongsS) {
        this.belongsS = belongsS;
    }

    public String generateRow() {
        return (isBelongsS() ? "<tr class=\"hit-yes\">" : "<tr class=\"hit-no\">") +
                "<td>" + Math.round(getX()*1000)/1000d + "</td>" +
                "<td>" + Math.round(getY()*1000)/1000d + "</td>" +
                "<td>" + Math.round(getR()*1000)/1000d + "</td>" +
                "<td>" + getCurrentTime() + "</td>" +
                "<td>" + getExecutionTime() + "</td>" +
                "<td>" + (isBelongsS() ? "Yes" : "No") + "</td>";

    }
}
