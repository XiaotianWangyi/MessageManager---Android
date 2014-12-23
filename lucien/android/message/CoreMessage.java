package com.youyou.uucar.core3.message;

import com.youyou.uucar.Utils.PB.BaseModel;

import java.util.List;

/**
 * Created by wangyi on 12/23/14.
 */
public interface CoreMessage {
    void handleWhisper(Whisper wsp);

    public static class Whisper {
        public int ret;
        public Object[] others;

        public Whisper(int ret, Object... others) {
            this.ret = ret;
            this.others = others;
        }
    }
}
