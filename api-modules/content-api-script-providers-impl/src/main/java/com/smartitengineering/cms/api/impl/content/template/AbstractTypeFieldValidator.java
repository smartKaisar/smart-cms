/*
 *
 * This is a simple Content Management System (CMS)
 * Copyright (C) 2010  Imran M Yousuf (imyousuf@smartitengineering.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.smartitengineering.cms.api.impl.content.template;

import com.smartitengineering.cms.api.content.Field;
import com.smartitengineering.cms.api.exception.InvalidTemplateException;
import com.smartitengineering.cms.api.workspace.ValidatorTemplate;
import com.smartitengineering.cms.api.content.template.FieldValidator;
import com.smartitengineering.cms.api.content.template.TypeFieldValidator;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author imyousuf
 */
public abstract class AbstractTypeFieldValidator implements TypeFieldValidator {

  protected final transient Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public boolean isValid(ValidatorTemplate template, Field field, Map<String, String> params) {
    FieldValidator validator;
    try {
      validator = getValidator(template);
    }
    catch (InvalidTemplateException ex) {
      logger.warn("Could not get validator!", ex);
      validator = null;
    }
    if (validator == null) {
      if (logger.isInfoEnabled()) {
        logger.info("Validator not available!");
      }
      return true;
    }
    return validator.isValidFieldValue(field, params);
  }
}
