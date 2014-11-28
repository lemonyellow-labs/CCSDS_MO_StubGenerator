/* ----------------------------------------------------------------------------
 * Copyright (C) 2013      European Space Agency
 *                         European Space Operations Centre
 *                         Darmstadt
 *                         Germany
 * ----------------------------------------------------------------------------
 * System                : CCSDS MO Service Stub Generator
 * ----------------------------------------------------------------------------
 * Licensed under the European Space Agency Public License, Version 2.0
 * You may not use this file except in compliance with the License.
 *
 * Except as expressly set forth in this License, the Software is provided to
 * You on an "as is" basis and without warranties of any kind, including without
 * limitation merchantability, fitness for a particular purpose, absence of
 * defects or errors, accuracy or non-infringement of intellectual property rights.
 * 
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * ----------------------------------------------------------------------------
 */
package esa.mo.tools.stubgen;

import esa.mo.tools.stubgen.specification.AttributeTypeDetails;
import esa.mo.tools.stubgen.specification.CompositeField;
import esa.mo.tools.stubgen.specification.StdStrings;
import esa.mo.tools.stubgen.writers.TargetWriter;
import esa.mo.tools.stubgen.xsd.TypeReference;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.maven.plugin.logging.Log;

/**
 * Base generator class for generators that product documents rather than programming language APIs.
 */
public abstract class GeneratorDocument extends GeneratorBase
{
  /**
   * Constructor.
   *
   * @param logger The logger.
   * @param config The configuration to use.
   */
  protected GeneratorDocument(Log logger, GeneratorConfiguration config)
  {
    super(logger, config);

    addAttributeType(StdStrings.MAL, StdStrings.BLOB, true, "Blob", "");
    addAttributeType(StdStrings.MAL, StdStrings.BOOLEAN, true, "Boolean", "");
    addAttributeType(StdStrings.MAL, StdStrings.DOUBLE, true, "Double", "");
    addAttributeType(StdStrings.MAL, StdStrings.DURATION, true, "Duration", "");
    addAttributeType(StdStrings.MAL, StdStrings.FLOAT, true, "Float", "");
    addAttributeType(StdStrings.MAL, StdStrings.INTEGER, true, "Integer", "");
    addAttributeType(StdStrings.MAL, StdStrings.IDENTIFIER, true, "Identifier", "");
    addAttributeType(StdStrings.MAL, StdStrings.LONG, true, "Long", "");
    addAttributeType(StdStrings.MAL, StdStrings.OCTET, true, "Octet", "");
    addAttributeType(StdStrings.MAL, StdStrings.SHORT, true, "Short", "");
    addAttributeType(StdStrings.MAL, StdStrings.UINTEGER, true, "UInteger", "");
    addAttributeType(StdStrings.MAL, StdStrings.ULONG, true, "ULong", "");
    addAttributeType(StdStrings.MAL, StdStrings.UOCTET, true, "UOctet", "");
    addAttributeType(StdStrings.MAL, StdStrings.USHORT, true, "UShort", "");
    addAttributeType(StdStrings.MAL, StdStrings.STRING, true, "String", "");
    addAttributeType(StdStrings.MAL, StdStrings.TIME, true, "Time", "");
    addAttributeType(StdStrings.MAL, StdStrings.FINETIME, true, "FineTime", "");
    addAttributeType(StdStrings.MAL, StdStrings.URI, true, "URI", "");
  }

  @Override
  protected CompositeField createCompositeElementsDetails(TargetWriter file, boolean checkType, String fieldName, TypeReference elementType, boolean isStructure, boolean canBeNull, String comment)
  {
    CompositeField ele;
    String typeName = elementType.getName();
    if (isAttributeType(elementType))
    {
      AttributeTypeDetails details = getAttributeDetails(elementType);
      ele = new CompositeField(details.getTargetType(), elementType, fieldName, elementType.isList(), canBeNull, false, StdStrings.MAL, "", "", false, "", comment);
    }
    else
    {
      String fqTypeName = typeName;
      ele = new CompositeField(fqTypeName, elementType, fieldName, elementType.isList(), canBeNull, false, elementType.getArea(), "", elementType.getService(), false, "", comment);
    }
    return ele;
  }

  /**
   * Splits a supplied string on any CRs or double spaces.
   *
   * @param srcArr Source string array to append to.
   * @param str String to split.
   * @return The updated source array.
   */
  protected static List<String> splitString(List<String> srcArr, String str)
  {
    if (null == srcArr)
    {
      srcArr = new LinkedList<String>();
    }
    if (null != str)
    {
      srcArr.addAll(Arrays.asList(str.split("(  |\n)")));
    }
    return srcArr;
  }
}
