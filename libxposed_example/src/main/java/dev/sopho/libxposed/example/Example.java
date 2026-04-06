package dev.sopho.libxposed.example;

import androidx.annotation.NonNull;

import io.github.libxposed.api.XposedInterface;
import io.github.libxposed.api.XposedModule;

public class Example extends XposedModule {

    /**
     * Instantiates a new Xposed module.<br/>
     * When the module is loaded into the target process, the constructor will be called.
     *
     * @param base  The implementation interface provided by the framework, should not be used by the module
     * @param param Information about the process in which the module is loaded
     */
    public Example(@NonNull XposedInterface base, @NonNull ModuleLoadedParam param) {
        super(base, param);
    }

    @Override
    public void onPackageLoaded(@NonNull PackageLoadedParam param) {
        super.onPackageLoaded(param);
        this.log("onPackageLoaded" + getApplicationInfo().packageName);
    }
}
