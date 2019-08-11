package utils.helper.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import androidx.annotation.NonNull;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MethodRef {
    @NonNull String name();
    @NonNull Class[] value();
}
