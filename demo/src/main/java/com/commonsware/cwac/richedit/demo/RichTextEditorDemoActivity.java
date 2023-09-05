/***
 Copyright (c) 2012 CommonsWare, LLC

 Licensed under the Apache License, Version 2.0 (the "License"); you may
 not use this file except in compliance with the License. You may obtain
 a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.commonsware.cwac.richedit.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;

import com.commonsware.cwac.richedit.*;

import java.util.List;

public class RichTextEditorDemoActivity extends Activity
        implements ColorPicker {
    private static final int COLOR_REQUEST = 1337;
    private RichEditText editor = null;
    private Button actionStyleBold;
    private Button actionStyleItalic;
    private Button actionStyleUnderline;
    private ColorPickerOperation colorPickerOp = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        editor = findViewById(R.id.editor);
        actionStyleBold = findViewById(R.id.actionStyleBold);
        actionStyleItalic = findViewById(R.id.actionStyleItalic);
        actionStyleUnderline = findViewById(R.id.actionStyleUnderline);
        editor.setColorPicker(this);
        setupViews();
    }

    private void setupViews(){
        actionStyleBold.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    editor.toggleEffect(RichEditText.BOLD);
                    view.setPressed(editor.isEffectActive(RichEditText.BOLD));
                }
                return true;
            }
        });
        actionStyleItalic.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    editor.toggleEffect(RichEditText.ITALIC);
                    view.setPressed(editor.isEffectActive(RichEditText.ITALIC));
                }
                return true;
            }
        });
        actionStyleUnderline.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    editor.toggleEffect(RichEditText.UNDERLINE);
                    view.setPressed(editor.isEffectActive(RichEditText.UNDERLINE));
                }
                return true;
            }
        });
        editor.setOnSelectionChangedListener(new RichEditText.OnSelectionChangedListener() {
            @Override
            public void onSelectionChanged(int start, int end, List<Effect<?>> effects) {
                actionStyleBold.setPressed(editor.isEffectActive(RichEditText.BOLD));
                actionStyleItalic.setPressed(editor.isEffectActive(RichEditText.ITALIC));
                actionStyleUnderline.setPressed(editor.isEffectActive(RichEditText.UNDERLINE));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);

        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_link:
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    @Override
    public boolean pick(ColorPickerOperation op) {

        return (true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent result) {
        int color = 0;

        if (colorPickerOp != null && requestCode == COLOR_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
            }

            if (color == 0) {
                colorPickerOp.onPickerDismissed();
            } else {
                colorPickerOp.onColorPicked(color);
            }

            colorPickerOp = null;
        }
    }
}
