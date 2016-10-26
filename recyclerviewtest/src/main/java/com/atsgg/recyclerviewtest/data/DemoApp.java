package com.atsgg.recyclerviewtest.data;

import java.util.ArrayList;

/**
 * Created by Mr.W on 2016/8/23.
 */
public class DemoApp {
    //获取要显示的数据（初始化数据）
    public static ArrayList<SampleModel> getSampleDate(int size) {
        ArrayList<SampleModel> sampleData = new ArrayList<SampleModel>(size);
        for (int i = 0; i < size; i++) {
            sampleData.add(new SampleModel("" + i ));
        }
        return sampleData;
    }
}
