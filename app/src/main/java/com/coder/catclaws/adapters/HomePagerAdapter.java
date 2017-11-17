/*
 *
 *  MIT License
 *
 *  Copyright (c) 2017 Alibaba Group
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.coder.catclaws.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.utils.ViewSize;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.Screen;
import com.tmall.ultraviewpager.transformer.DensityUtil;

import java.util.List;

/**
 * Created by mikeafc on 15/11/26.
 */
public class HomePagerAdapter extends PagerAdapter {

    Context context;
    List<HomeModel.DataBean.BannersBean> bannersBeanList;

    public HomePagerAdapter(Context context, List<HomeModel.DataBean.BannersBean> bannersBeans) {
        this.context = context;
        this.bannersBeanList = bannersBeans;
    }

    @Override
    public int getCount() {
        return bannersBeanList == null ? 0 : bannersBeanList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View linearLayout = LayoutInflater.from(container.getContext()).inflate(R.layout
                        .home_banner_item,
                null);
        SimpleDraweeView image = linearLayout.findViewById(R.id.image);
        ViewSize.fixedSize(image, (int) (Screen.getWidth(context) * 5f / 7f), 450f / 780f);
        ViewSize.setSize(image, (int) (Screen.getWidth(context) * 5f / 7f), (int) (Screen.getWidth(context) * 5f / 7f *
                450f / 780f - DensityUtil.dip2px(context, 12)));
        ImageLoader.getInstance().loadImage(image, bannersBeanList.get(position).getImg());
        container.addView(linearLayout);

        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    public void updateData(List<HomeModel.DataBean.BannersBean> bannersBeans) {
        this.bannersBeanList = bannersBeans;
        notifyDataSetChanged();
    }
}
