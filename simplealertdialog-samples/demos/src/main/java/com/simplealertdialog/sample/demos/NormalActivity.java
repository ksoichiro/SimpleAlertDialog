/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simplealertdialog.sample.demos;

import com.simplealertdialog.SimpleAlertDialog;
import com.simplealertdialog.SimpleAlertDialogFragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NormalActivity extends Activity
        implements SimpleAlertDialog.OnClickListener,
        SimpleAlertDialog.OnNeutralButtonClickListener,
        SimpleAlertDialog.OnItemClickListener,
        SimpleAlertDialog.SingleChoiceArrayItemProvider,
        SimpleAlertDialog.ListProvider,
        SimpleAlertDialog.ViewProvider {

    private static final int REQUEST_CODE_BUTTONS = 1;
    private static final int REQUEST_CODE_3_BUTTONS = 2;
    private static final int REQUEST_CODE_ITEMS = 3;
    private static final int REQUEST_CODE_ICON_ITEMS = 4;
    private static final int REQUEST_CODE_SINGLE_CHOICE_LIST = 5;
    private static final int REQUEST_CODE_ADAPTER = 6;
    private static final int REQUEST_CODE_VIEW = 7;
    private static final int REQUEST_CODE_EDIT_TEXT = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    protected void initLayout() {
        setContentView(R.layout.activity_support);
        initButtons();
    }

    protected void initButtons() {
        findViewById(R.id.btn_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setMessage("Hello world!")
                        .setPositiveButton(android.R.string.ok)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_message_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                TypedArray a = getTheme().obtainStyledAttributes(new int[] { R.attr.icon });
                int iconResId = a.getResourceId(0, 0);
                a.recycle();
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Hello world!")
                        .setIcon(iconResId)
                        .setMessage("Hello world!")
                        .setPositiveButton(android.R.string.ok)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_buttons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Hello world!")
                        .setMessage("Hello world!")
                        .setPositiveButton(android.R.string.ok)
                        .setNegativeButton(android.R.string.cancel)
                        .setRequestCode(REQUEST_CODE_BUTTONS)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_3_buttons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Hello world!")
                        .setMessage("Hello world!")
                        .setPositiveButton(android.R.string.ok)
                        .setNeutralButton(R.string.neutral)
                        .setNegativeButton(android.R.string.cancel)
                        .setRequestCode(REQUEST_CODE_3_BUTTONS)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_items).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Choose one")
                        .setItems(R.array.single_choice)
                        .setRequestCode(REQUEST_CODE_ITEMS)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_icon_items).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Choose one")
                        .setItems(R.array.icon_items, getIcons())
                        .setRequestCode(REQUEST_CODE_ICON_ITEMS)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_single_choice_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Choose one")
                        .setSingleChoiceCheckedItem(0)
                        .setRequestCode(REQUEST_CODE_SINGLE_CHOICE_LIST)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_adapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Choose your favorite")
                        .setUseAdapter(true)
                        .setRequestCode(REQUEST_CODE_ADAPTER)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Enter something")
                        .setUseView(true)
                        .setPositiveButton(android.R.string.ok)
                        .setRequestCode(REQUEST_CODE_VIEW)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_edit_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTitle("Enter password")
                        .setEditText("", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
                        .setPositiveButton(android.R.string.ok)
                        .setRequestCode(REQUEST_CODE_EDIT_TEXT)
                        .create().show(getFragmentManager(), "dialog");
            }
        });

        findViewById(R.id.btn_themed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new SimpleAlertDialogFragment.Builder()
                        .setTheme(R.style.SimpleAlertDialogCustomTheme)
                        .setMessage("Hello world!")
                        .setPositiveButton(android.R.string.ok)
                        .create().show(getFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public void onDialogPositiveButtonClicked(final SimpleAlertDialog dialog, int requestCode,
            final View view) {
        if (requestCode == REQUEST_CODE_BUTTONS) {
            Toast.makeText(this, "OK button clicked", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_3_BUTTONS) {
            Toast.makeText(this, "OK button clicked (3 buttons)", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_VIEW) {
            String text = ((EditText) view.findViewById(R.id.text)).getText().toString();
            Toast.makeText(this, "You typed: " + text, Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_EDIT_TEXT) {
            String text = ((EditText) view.findViewById(android.R.id.text1)).getText().toString();
            Toast.makeText(this, "You typed: " + text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogNegativeButtonClicked(final SimpleAlertDialog dialog, int requestCode,
            final View view) {
        if (requestCode == REQUEST_CODE_BUTTONS) {
            Toast.makeText(this, "Cancel button clicked", Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_3_BUTTONS) {
            Toast.makeText(this, "Cancel button clicked (3 buttons)", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogNeutralButtonClicked(SimpleAlertDialog dialog, int requestCode, View view) {
        if (requestCode == REQUEST_CODE_3_BUTTONS) {
            Toast.makeText(this, "Neutral button clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(SimpleAlertDialog dialog, int requestCode, int which) {
        if (requestCode == REQUEST_CODE_ITEMS) {
            Toast.makeText(this,
                    getResources().getTextArray(R.array.single_choice)[which] + " selected",
                    Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_CODE_ICON_ITEMS) {
            Toast.makeText(this,
                    getResources().getTextArray(R.array.icon_items)[which] + " selected",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public CharSequence[] onCreateSingleChoiceArray(final SimpleAlertDialog dialog, int requestCode) {
        if (requestCode == REQUEST_CODE_SINGLE_CHOICE_LIST) {
            return getResources().getTextArray(R.array.single_choice);
        }
        return null;
    }

    @Override
    public void onSingleChoiceArrayItemClick(final SimpleAlertDialog dialog, int requestCode,
            int position) {
        if (requestCode == REQUEST_CODE_SINGLE_CHOICE_LIST) {
            Toast.makeText(this,
                    getResources().getTextArray(R.array.single_choice)[position] + " selected",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public ListAdapter onCreateList(SimpleAlertDialog dialog, int requestCode) {
        if (requestCode == REQUEST_CODE_ADAPTER) {
            return new SweetsAdapter(this, Sweets.SWEETS_LIST);
        }
        return null;
    }

    @Override
    public void onListItemClick(SimpleAlertDialog dialog, int requestCode, int position) {
        if (requestCode == REQUEST_CODE_ADAPTER) {
            Toast.makeText(this,
                    Sweets.SWEETS_LIST.get(position).name + " selected",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(SimpleAlertDialog dialog, int requestCode) {
        if (requestCode == REQUEST_CODE_VIEW) {
            final View view = LayoutInflater.from(this).inflate(R.layout.view_editor, null);
            ((EditText) view.findViewById(R.id.text)).setText("Sample");
            return view;
        }
        return null;
    }

    protected int[] getIcons() {
        return new int[]{R.drawable.ic_action_aci_document3,
                R.drawable.ic_action_aci_edit,
                R.drawable.ic_action_aci_search,
        };
    }
}
