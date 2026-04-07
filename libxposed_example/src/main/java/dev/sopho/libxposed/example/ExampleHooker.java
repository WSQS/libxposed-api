package dev.sopho.libxposed.example;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;

import io.github.libxposed.api.XposedInterface;

public class ExampleHooker implements XposedInterface.Hooker {
    public static Example module;

    public static void before(@NonNull XposedInterface.BeforeHookCallback callback) {
        // Pre-hooking logic goes here
        if (callback.getThisObject() != null) {
            module.log("before: this:" + callback.getThisObject().getClass().getName() + " declaring:" + callback.getMember().getDeclaringClass() + " method:" + callback.getMember().getName());
        } else {
            module.log("before: declaring:" + callback.getMember().getDeclaringClass() + " method:" + callback.getMember().getName());
        }
        if (callback.getMember().getDeclaringClass().toString().equals("class com.stub.StubApp")) {
            module.log("find");
            if (callback.getMember().getName().matches("interface5")) {
                module.log("return for interface5");
                callback.returnAndSkip(null);
            }
        }
        if (callback.getMember().getDeclaringClass().toString().equals("class java.lang.System")) {
            String so_name = (String) callback.getArgs()[0];
            module.log(so_name);
            if (so_name.matches("/data/user/0/club.jijigugu.yiguan/.jiagu/libjiagu_64.so")) {
                callback.returnAndSkip(null);
            }

        }
        if (callback.getThisObject() != null && callback.getThisObject().getClass().getName().equals("com.stub.StubApp")) {
//            try {
//                Field field = callback.getThisObject().getClass().getDeclaredField("loadFromLib");
//                field.setAccessible(true);
//                field.setBoolean(null, true);
//                module.log("loadFromLib set finish");
//            } catch (NoSuchFieldException e) {
//                module.log("NoSuchMethodException:" + e);
//            } catch (Exception e) {
//                module.log("Exception:" + e);
//            }
        }

    }

    public static void after(@NonNull XposedInterface.AfterHookCallback callback) {
        // Post-hooking logic goes here
        if (callback.getThisObject() != null) {
            module.log("after: this:" + callback.getThisObject().getClass().getName() + " declaring:" + callback.getMember().getDeclaringClass() + " method:" + callback.getMember().getName());
        } else {
            module.log("after: declaring:" + callback.getMember().getDeclaringClass() + " method:" + callback.getMember().getName());
        }
        if (callback.getMember().getDeclaringClass().toString().equals("class com.tianyu.util.a")) {
            module.hookSystem();
        }

        if (callback.getThisObject() != null && callback.getThisObject().getClass().getName().equals("com.stub.StubApp")) {
            if (callback.getMember().getName().matches("interface5")) {
                callback.setThrowable(null);
            }
        }
        if (callback.getMember().getDeclaringClass().toString().equals("class com.stub.StubApp")) {
            if (callback.getMember().getName().matches("a")) {
                if(callback.getResult() == null) {
                    module.log("return of a is null");
                }else{
                    module.log("return of a:" + callback.getResult().getClass().getName());

                }

            }
        }

    }
}
