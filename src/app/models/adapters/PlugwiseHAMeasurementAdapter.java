package app.models.adapters;

import app.models.Measurement;

/**
 * PlugwiseHAMeasurementAdapter
 */
public class PlugwiseHAMeasurementAdapter implements IPlugwiseHAModelAdapter {

    private Measurement measurement;

    public PlugwiseHAMeasurementAdapter(Measurement measurement) {
        this.measurement = measurement;
    }
}