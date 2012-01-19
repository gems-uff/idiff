package motivatingexample;

public class Person {

    private String _name;
    private TelephoneNumber _officeTelephone = new TelephoneNumber();

    public String getName() {
        return _name;
    }

    public String getTelephoneNumber() {
        return _officeTelephone.getTelephoneNumber();
    }

    public TelephoneNumber getOfficeTelephone() {
        return _officeTelephone;
    }

    public void setName(String arg) {
        this._name = arg;
    }

    public void setOfficeTelephone(TelephoneNumber _officeTelephone) {
        this._officeTelephone = _officeTelephone;
    }
}
