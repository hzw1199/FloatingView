package com.wuadam.floatingview;

import android.view.View;

import androidx.annotation.IdRes;

public class FloatingViewConfig {
    public enum GRAVITY {
        LEFT_CENTER, LEFT_TOP, TOP_CENTER, TOP_RIGHT, RIGHT_CENTER, RIGHT_BOTTOM, BOTTOM_CENTER, LEFT_BOTTOM, CENTER
    }

    int paddingLeft, paddingTop, paddingRight, paddingBottom;
    int displayWidth, displayHeight;
    GRAVITY gravity;
    @IdRes int scaleHandlerId;
    View scaleHandler;

    private FloatingViewConfig(Builder builder) {
        this.paddingLeft = builder.paddingLeft;
        this.paddingTop = builder.paddingTop;
        this.paddingRight = builder.paddingRight;
        this.paddingBottom = builder.paddingBottom;

        this.displayWidth = builder.displayWidth;
        this.displayHeight = builder.displayHeight;

        this.gravity = builder.gravity;

        this.scaleHandlerId = builder.scaleHandlerId;
        this.scaleHandler = builder.scaleHandler;
    }


    public static class Builder {
        int paddingLeft, paddingTop, paddingRight, paddingBottom;
        int displayWidth = Integer.MAX_VALUE, displayHeight = Integer.MAX_VALUE;
        GRAVITY gravity = GRAVITY.LEFT_CENTER;
        @IdRes int scaleHandlerId;
        View scaleHandler;

        /**
         * set the padding of left
         * @param paddingLeft unit is DP
         */
        public Builder setPaddingLeft(int paddingLeft) {
            this.paddingLeft = paddingLeft;
            return this;
        }

        /**
         * set the padding of top
         *
         * @param paddingTop unit is DP
         */
        public Builder setPaddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
            return this;
        }

        /**
         * set the padding of right
         *
         * @param paddingRight unit is DP
         */
        public Builder setPaddingRight(int paddingRight) {
            this.paddingRight = paddingRight;
            return this;
        }

        /**
         * set the padding of bottom
         *
         * @param paddingBottom unit is DP
         */
        public Builder setPaddingBottom(int paddingBottom) {
            this.paddingBottom = paddingBottom;
            return this;
        }

        /**
         * Set the width of area where FloatingView is to show.
         * default: width of screen
         *
         * @param displayWidth
         */
        public Builder setDisplayWidth(int displayWidth) {
            this.displayWidth = displayWidth;
            return this;
        }

        /**
         * Set the height of area where FloatingView is to show.
         * default: height of screen - height of status bar
         *
         * @param displayHeight
         */
        public Builder setDisplayHeight(int displayHeight) {
            this.displayHeight = displayHeight;
            return this;
        }

        /**
         * Set the direction to display the FloatingView.
         *
         * @param gravity
         */
        public Builder setGravity(GRAVITY gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * Set the id of the view to scale. Lower priority than setScaleHandler.
         * Set to 0 to disable scaling.
         *
         * @param scaleHandlerId
         */
        public Builder setScaleHandlerId(@IdRes int scaleHandlerId) {
            this.scaleHandlerId = scaleHandlerId;
            return this;
        }

        /**
         * Set the view to scale. Higher priority than setScaleHandlerId.
         * Set to null to disable scaling.
         *
         * @param scaleHandler
         */
        public Builder setScaleHandler(View scaleHandler) {
            this.scaleHandler = scaleHandler;
            return this;
        }

        public FloatingViewConfig build() {
            return new FloatingViewConfig(this);
        }
    }
}
