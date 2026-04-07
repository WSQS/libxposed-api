package dev.sopho.libxposed.example;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.NonNull;

import java.lang.reflect.Method;

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
        ExampleHooker.module = this;
    }

    @Override
    public void onPackageLoaded(@NonNull PackageLoadedParam param) {
//        super.onPackageLoaded(param);
        this.log("onPackageLoaded:" + param.getPackageName());
        try {
            ClassLoader cl = param.getClassLoader();
            Method attachMethod = ContextWrapper.class.getDeclaredMethod("attachBaseContext", Context.class);
            hook(attachMethod, ExampleHooker.class);
            attachMethod = Application.class.getDeclaredMethod("attach", Context.class);
            hook(attachMethod, ExampleHooker.class);
            attachMethod = cl.loadClass("com.stub.StubApp").getDeclaredMethod("attachBaseContext", Context.class);
            hook(attachMethod, ExampleHooker.class);
            attachMethod = cl.loadClass("com.stub.StubApp").getDeclaredMethod("a", Context.class);
            hook(attachMethod, ExampleHooker.class);
            attachMethod = cl.loadClass("com.stub.StubApp").getDeclaredMethod("interface5", Application.class);
            hook(attachMethod, ExampleHooker.class);
            attachMethod = cl.loadClass("com.tianyu.util.a").getDeclaredMethod("b");
            hook(attachMethod, ExampleHooker.class);
            attachMethod = cl.loadClass("com.tianyu.util.DtcLoader").getDeclaredMethod("init");
            hook(attachMethod, ExampleHooker.class);
//            attachMethod = System.class.getDeclaredMethod("loadLibrary", String.class);
//            hook(attachMethod, ExampleHooker.class);
        } catch (Throwable t) {
            log("hook error: " + t);
        }
    }

    public void hookSystem(){
        this.log("hookSystem");
        try {
            Method attachMethod = System.class.getDeclaredMethod("loadLibrary", String.class);
            hook(attachMethod, ExampleHooker.class);
            attachMethod = System.class.getDeclaredMethod("load", String.class);
            hook(attachMethod, ExampleHooker.class);
        } catch (Throwable t) {
            log("hook error: " + t);
        }
    }

}
