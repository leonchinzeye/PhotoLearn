package com.mtech.parttimeone.photolearn.Adapter;

/**
 * Created by changling on 22/3/18.
 */

public class OptionItem {
        private String optionIndex;
        private String optionDetail;
        public boolean isAns = false;

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
}
