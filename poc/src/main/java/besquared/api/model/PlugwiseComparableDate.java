package besquared.api.model;

/**
 * PlugwiseComparableDate
 */
public interface PlugwiseComparableDate<T extends PlugwiseBaseModel> {
    public int compareDateWith(T hasModifiedDate);

    public boolean isOlderThan(T hasModifiedDate);

    public boolean isNewerThan(T hasModifiedDate);
}