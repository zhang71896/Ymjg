package com.yrj520.pfapp.ymjg.UI.filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Title:
 * Description:
 * Copyright:
 * Company:
 *
 * @author Rock
 * @version 1.0
 */

public class InputMaxLimitFilter implements InputFilter {

        private int min, max;

        public InputMaxLimitFilter(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputMaxLimitFilter(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }

}
