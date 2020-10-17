package cs4330.pricewatcher;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

/**
 * A simple {@link DialogFragment} subclass.
 */
public class DialogFragment extends androidx.fragment.app.DialogFragment {

    public DialogFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Created by Erik Rivera and Jesus M. Hernandez");
        // Create the AlertDialog object and return it
        return builder.create();
    }



}