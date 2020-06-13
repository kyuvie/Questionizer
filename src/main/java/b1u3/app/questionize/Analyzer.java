package b1u3.app.questionize;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Analyzer {
    private static HashMap<String, Analyzer> _clsnameToInstance = new HashMap();
    private static Object _lock = new Object();

    protected Analyzer() {
        synchronized (_lock) {
            String clsName = this.getClass().getName();
            if (_clsnameToInstance.get(clsName) != null) {
                throw new RuntimeException("Already created: " + clsName);
            }
            _clsnameToInstance.put(clsName, this);
        }
    }

    public static Analyzer getInstance(String clsname) {
        // https://www.hyuki.com/techinfo/singleton.html
        synchronized (_lock) {
            // maybe raise Cast Exception when this is compiled.
            Analyzer obj = (Analyzer)_clsnameToInstance.get(clsname);
            if (obj == null) {
                try {
                    Class cls = Class.forName(clsname);
                    obj = (Analyzer)cls.getDeclaredConstructor().newInstance();
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(clsname + "'s constructor is not found.");
                } catch (SecurityException e) {
                    throw new RuntimeException(clsname + " can't be accessed.");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(clsname + " can't be accessed.");
                } catch (InstantiationException e) {
                    throw new RuntimeException(clsname + " can't be instantiated.");
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException(clsname + " doesn't match arguments.");
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(clsname + " can't be found.");
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(clsname + " ????????? ");
                }
            }
            return obj;
        }
    }

    public abstract ArrayList<String> analyzeLine(String line, String sep);
    public abstract ArrayList<ArrayList<String>> analyzeAll(String all, String sep);
}
