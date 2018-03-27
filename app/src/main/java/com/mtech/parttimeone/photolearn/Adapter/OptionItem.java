package com.mtech.parttimeone.photolearn.Adapter;

import android.util.Log;

/**
 * Created by changling on 22/3/18.
 */

public class OptionItem {
        private String optionIndex;
        private String optionDetail;
        private boolean isAns = false;

        public String getOptionIndex(){
            return optionIndex;
        }

        public void setOptionIndex(String index){
            this.optionIndex = index;
        }

        public String getOptionDetail(){
            return optionDetail;
        }

        public void setOptionDetail(String detail){
            this.optionDetail = detail;
        }

        public boolean getAns(){
            return this.isAns;
        }

        public void setAns(boolean value){
            Log.d("my_log", this.optionDetail);
            this.isAns = value;
        }
}
