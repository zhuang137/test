package utils.helper.reflect;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Retention(RetentionPolicy.RUNTIME)
public @interface RefTypeItem {
    @NonNull String name();
}
