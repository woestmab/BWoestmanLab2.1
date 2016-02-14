/*
 * Copyright (C) 2012 The Android Open Source Project
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
package com.woestmanBrian.bWoestmanLab2_1.fragments;

import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ArticleFragment extends Fragment implements StringConstants {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    private EditText    mEtArticle;
    private Button      mBtnCancel;
    private Button      mBtnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.article_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle) {

        mEtArticle  = (EditText)    view.findViewById(R.id.article);
        mBtnCancel  = (Button)      view.findViewById(R.id.btn_cancel);
        mBtnSave    = (Button)      view.findViewById(R.id.btn_save);

        mBtnCancel.setOnClickListener(new View.OnClickListener(){

            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                returnToHeadlines();
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener(){
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v)
            {
                saveIpsum();
                returnToHeadlines();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        EditText article = (EditText) getActivity().findViewById(R.id.article);

        String content;

        //TODO: Ask why there is an excessive amount of casting required here
        SingletonIpsum singletonIpsum = SingletonIpsum.getSingletonIpsum();
        ArrayList ipsums = (ArrayList) singletonIpsum.getIpsums();
        Ipsum ipsum = (Ipsum) ipsums.get(position);
        content = ipsum.getContent();

        article.setText(content);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void saveIpsum() {
        String content = mEtArticle.getText().toString();
        SingletonIpsum singleton = SingletonIpsum.getSingletonIpsum();
        ArrayList<Ipsum> ipsums = (ArrayList) singleton.getIpsums();

        ipsums.get(mCurrentPosition).setContent(content);
    }

    public void returnToHeadlines() {

        HeadlinesFragment headlinesFragment = new HeadlinesFragment();

        android.support.v4.app.FragmentTransaction transaction =
                getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, headlinesFragment)
                .addToBackStack(null)
                .commit();
    }
}