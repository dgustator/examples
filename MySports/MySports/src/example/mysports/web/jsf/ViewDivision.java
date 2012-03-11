/*******************************************************************************
 * Copyright (c) 2010-2011 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the 
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0 
 * which accompanies this distribution. 
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at 
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *  dclarke - EclipseLink 2.3 - MySports Demo Bug 344608
 ******************************************************************************/
package example.mysports.web.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import example.mysports.model.Division;

/**
 * JSF managed bean to view a division with its teams.
 * 
 * @author dclarke
 * @since EclipseLink 2.3.0
 */
@ManagedBean
@RequestScoped
public class ViewDivision extends BaseManagedBean {

    protected static final String PAGE = "view-division";

    public Division getDivision() {
        return getRepository().getCurrentDivision();
    }

    public String view() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest myRequest = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        String value = myRequest.getParameter("division-id");

        if (value != null && !value.isEmpty()) {
            Division div = getRepository().find(Division.class, Integer.valueOf(value));
            getRepository().setCurrentDivision(div);
        }

        return PAGE;
    }
}