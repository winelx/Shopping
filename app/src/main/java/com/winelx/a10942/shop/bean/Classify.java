package com.winelx.a10942.shop.bean;

/**
 * @name Shopping-master
 * @class name：com.winelx.a10942.shop.bean
 * @class describe
 * @anthor 10942 QQ:1032006226
 * @time 2017/4/12 0012 16:32
 * @change
 * @chang time
 * @class describe
 */
public class Classify {
    String texts;
    String imge;

    public Classify(String imge, String texts) {
        this.imge = imge;
        this.texts = texts;

    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }

    public String getImge() {
        return imge;
    }

    public void setImge(String imge) {
        this.imge = imge;
    }
}
