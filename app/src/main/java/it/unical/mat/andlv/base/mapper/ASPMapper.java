package it.unical.mat.andlv.base.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class ASPMapper {
	
	private static ASPMapper mapper;
	
	private Map<String,Class<?>> predicateClass;
	
	private ASPMapper(){predicateClass=new HashMap<>();}
	
	public String registerClass(Class<?> cl){
		String predicate=cl.getAnnotation(Predicate.class).value();
		predicateClass.put(predicate, cl);
		return predicate;
	}
	
	public Class<?> getClass(String predicate){
		return predicateClass.get(predicate);
	}
	
	public String getAtom(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalTermException{
		String predicate=registerClass(obj.getClass());
		String atom=predicate+"(";
		HashMap<Integer, Object> mapTerm=new HashMap<>();
		for(Field field:obj.getClass().getDeclaredFields()){
			if(field.isAnnotationPresent(Term.class)){
				Object value=obj.getClass().getMethod("get"+Character.toUpperCase(field.getName().charAt(0))+field.getName().substring(1)).invoke(obj);
				mapTerm.put(field.getAnnotation(Term.class).value(), value);
			}
		}
		for(int i=0;i<mapTerm.size();i++){
			if(i!=0)
				atom+=",";
			Object objectTerm=mapTerm.get(i);
			if(objectTerm==null)throw new IllegalTermException("Wrong term number of class "+obj.getClass().getName());
			if(objectTerm instanceof Integer){
				atom+=objectTerm+"";
			}else
				atom+="\""+objectTerm.toString()+"\"";
			
		}
		atom+=")";
		return atom;
	}
	
	public Object getObject(String atom) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException{
		String predicate=atom.substring(0,atom.indexOf("("));
		Class<?> cl=getClass(predicate);
		Object obj=cl.newInstance();
		//FIXME Not work with "a("asd,"). fix the split
		String[] paramiter=atom.substring(atom.indexOf("(")+1, atom.lastIndexOf(")")).split(",");
		for(Field field:obj.getClass().getDeclaredFields()){
			if(field.isAnnotationPresent(Term.class)){
				int term=field.getAnnotation(Term.class).value();
				String nameMethod="set"+Character.toUpperCase(field.getName().charAt(0))+field.getName().substring(1);
				
				//FIXME call one time
				for(Method method:cl.getMethods())
					if(method.getName().equals(nameMethod)){

						if(method.getParameterTypes()[0].getName().equals(int.class.getName()) || method.getParameterTypes()[0].getName().equals(Integer.class.getName()))
							method.invoke(obj, Integer.valueOf(paramiter[term]));
						
						else
							method.invoke(obj, paramiter[term]);
						
						break;
					}
				}
		}

		return obj;
	}

	public static ASPMapper getInstance(){if(mapper==null)mapper=new ASPMapper(); return mapper;}
}
