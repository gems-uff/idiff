package motivatingexample;

public class TelephoneNumber {

    private String _areaCode;
    private String _number;

    public String getAreaCode() {
        return _areaCode;
    }

    public void setAreaCode(String arg) {
        this._areaCode = arg;
    }

    public String getNumber() {
        return _number;
    }

    public void setNumber(String _number) {
        this._number = _number;
    }

    public String getTelephoneNumber() {
        return ("(" + _areaCode + ")" + _number);
    }
}
