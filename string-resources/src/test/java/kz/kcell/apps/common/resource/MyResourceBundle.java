package kz.kcell.apps.common.resource;

import kz.kcell.apps.common.Language;

enum MyResourceBundle implements ResourceBundle {
    res1("ru_res1"),
    res2("ru_res2", "kk_res2"),
    res3("ru_res3", "kk_res3", "en_res3");

    private String _ru;
    private String _kk;
    private String _en;

    MyResourceBundle(String _ru) {
        this._ru = _ru;
        this._kk = _ru;
        this._en = _ru;
    }

    MyResourceBundle(String _ru, String _kk) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _ru;
    }

    MyResourceBundle(String _ru, String _kk, String _en) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _en;
    }

    void set(String _ru) {
        this._ru = _ru;
        this._kk = _ru;
        this._en = _ru;
    }

    void set(String _ru, String _kk) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _ru;
    }

    void set(String _ru, String _kk, String _en) {
        this._ru = _ru;
        this._kk = _kk;
        this._en = _en;
    }


    public String _ru() {
        return _ru;
    }

    public String _kk() {
        return _kk;
    }

    public String _en() {
        return _en;
    }

    @Override
    public void setValue(Language language, String value) {
        switch (language) {
            case KZ:
            case KK: _kk = value; break;
            case RU: _ru = value; break;
            case EN: _en = value; break;
            default: _ru = value;
        }
    }

}

 