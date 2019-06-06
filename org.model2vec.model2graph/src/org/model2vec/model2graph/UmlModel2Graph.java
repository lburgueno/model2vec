package org.model2vec.model2graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.impl.ClassImpl;
import org.eclipse.uml2.uml.internal.impl.ModelImpl;
import org.eclipse.uml2.uml.internal.impl.PropertyImpl;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.UMLPackage;

public class UmlModel2Graph {
	
	public static void main(String[] args) {
		
/*
		 	args[0] = 	inputs/HelloWorld.uml
			 			inputs/Library.uml
						inputs/org.model2vec.casestudies.cars.uml
						inputs/org.modelexecution.quantitytypes.java.uml
*/
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("uml", new UMLResourceFactoryImpl());

		URI uri2 = URI.createURI(args[0]);
		Resource resource = resourceSet.getResource(uri2, true);

		TreeIterator<EObject> allContents = resource.getAllContents();
		while (allContents.hasNext()) {
			EObject eo = allContents.next();
			if (eo instanceof PropertyImpl) {
				PropertyImpl p = (PropertyImpl) eo;
				if (p.getName()!=null) {
					if (p.getOwner() instanceof ClassImpl && p.getType() instanceof ClassImpl) {
						System.out.print(p.getName()+" ");
						ClassImpl source = (ClassImpl) p.getOwner();
						System.out.print(source.getName() + " ");
						System.out.println(p.getType().getName());
					}
				}
			}
		}

		System.out.println("\n\nDone!");

	}
	
}
