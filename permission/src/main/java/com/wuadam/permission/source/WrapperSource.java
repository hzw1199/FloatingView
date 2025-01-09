/*
 * Copyright 2019 Zhenjie Yan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wuadam.permission.source;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Zhenjie Yan on 2/22/19.
 */
public class WrapperSource extends Source {

    private Source mSource;

    public WrapperSource(Source source) {
        this.mSource = source;
    }

    @Override
    public Context getContext() {
        return mSource.getContext();
    }

    @Override
    public void startActivity(Intent intent) {
        mSource.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mSource.startActivityForResult(intent, requestCode);
    }

    @Override
    public boolean isShowRationalePermission(String permission) {
        return mSource.isShowRationalePermission(permission);
    }
}