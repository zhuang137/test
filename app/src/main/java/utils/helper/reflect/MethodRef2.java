package utils.helper.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MethodRef2 {
    @NonNull String name();
    @NonNull RefTypeItem[] value();
}
