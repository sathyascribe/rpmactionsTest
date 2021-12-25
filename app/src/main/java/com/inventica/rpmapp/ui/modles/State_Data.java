package com.inventica.rpmapp.ui.modles;

import com.google.gson.annotations.SerializedName;

public class State_Data {

        @SerializedName("Text")
        public String Text;

        @SerializedName("Value")
        public String Value;

        public String getText() {
            return Text;
        }

        public void setText(String text) {
            Text = text;
        }

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }
        public String toString()
        {
            return( this.getText() );
        }
}
