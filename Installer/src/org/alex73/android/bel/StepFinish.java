package org.alex73.android.bel;

import android.text.util.Linkify;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StepFinish extends Step {
    private final CharSequence text;
    private final boolean showRebootLabel;
    private Button btnReboot;

    public StepFinish(AndroidBel ui, CharSequence text, boolean showRebootLabel) {
        super(ui);
        this.text = text;
        this.showRebootLabel = showRebootLabel;
    }

    protected void show() {
        MyLog.log("FINISHED");
        phase = "stepFinish";
        ui.setContentView(R.layout.step5);

        btnReboot = (Button) ui.findViewById(R.id.btnReboot);
        // btnReboot.setVisibility(showRebootLabel ? View.VISIBLE : View.INVISIBLE);
        btnReboot.setVisibility(View.INVISIBLE);

        textLog = (TextView) ui.findViewById(R.id.textLog5);
        textLog.setText(text + ui.getResources().getText(R.string.textInfo).toString());
        Linkify.addLinks(textLog, Linkify.ALL);

        btnNext = (Button) ui.findViewById(R.id.btnNext5);
        btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyLog.log("EXIT");
                ui.finish();
            }
        });
        btnReboot.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                btnReboot.setVisibility(View.INVISIBLE);
                try {
                    MyLog.log("REBOOT");
                    ExecSu.exec("reboot");
                } catch (Exception ex) {
                }
            }
        });
    }
}
