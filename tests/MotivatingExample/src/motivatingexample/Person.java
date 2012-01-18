package motivatingexample;

public class Person {

    private String _name;
    private String _officeAreaCode;
    private String _officeNumber;

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return _name;
    }

    public void setOfficeAreaCode(String arg) {
        this._officeAreaCode = arg;
    }

    public String getOfficeAreaCode() {
        return _officeAreaCode;
    }

    public String getTelephoneNumber() {
        return ("(" + _officeAreaCode + ")" + _officeNumber);
    }

    public void setOfficeNumber(String arg) {
        this._officeNumber = arg;
    }

    public String getOfficeNumber() {
        return _officeNumber;
    }
}
