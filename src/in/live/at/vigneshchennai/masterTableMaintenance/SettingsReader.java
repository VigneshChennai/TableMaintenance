/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.live.at.vigneshchennai.masterTableMaintenance;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author vignesh
 */
public class SettingsReader {

    /**
     * @param args the command line arguments
     */
    private static class SettingsHandler extends DefaultHandler {

        private MasterTableManager mtm = new MasterTableManager();
        private boolean started = false;
        private boolean ended = false;
        private boolean insideCabinetTag = false;
        private boolean insideTableTag = false;
        private boolean displayNameTag = false;
        private boolean idTag = false;
        private boolean tableNameTag = false;
        private boolean insideColumnsTag = false;
        private boolean insideColumnTag = false;
        private boolean uniqueColumn = false;
        private boolean insidePermittedgroupTag = false;
        private MasterTable mt;

        public MasterTableManager getMasterTableManager() {
            return mtm;
        }

        @Override
        public void startElement(String uri, String localName,
                String qName, Attributes attributes)
                throws SAXException {

            if (qName.equalsIgnoreCase("settings")) {
                started = true;
            } else if (qName.equalsIgnoreCase("table")) {
                mt = new MasterTable();
                insideTableTag = true;
            }

            if (started && !ended) {
                if (insideTableTag) {
                    if (qName.equalsIgnoreCase("displayname")) {
                        displayNameTag = true;
                    } else if (qName.equalsIgnoreCase("tablename")) {
                        tableNameTag = true;
                    } else if (qName.equalsIgnoreCase("columns")) {
                        insideColumnsTag = true;
                    } else if (qName.equalsIgnoreCase("cabinet")) {
                        insideCabinetTag = true;
                    } else if (qName.equalsIgnoreCase("id")) {
                        idTag = true;
                    } else if (qName.equalsIgnoreCase("permittedgroup")) {
                        insidePermittedgroupTag = true;
                    }

                    if (insideColumnsTag) {
                        if (qName.equalsIgnoreCase("column")) {
                            insideColumnTag = true;
                            String value = attributes.getValue("unique");
                            if (value != null && value.equalsIgnoreCase("true")) {
                                uniqueColumn = true;
                            }
                        }
                    }
                }
            }

        }

        @Override
        public void endElement(String uri, String localName,
                String qName)
                throws SAXException {
            if (qName.equalsIgnoreCase("settings")) {
                ended = true;
            } else if (qName.equalsIgnoreCase("table")) {
                insideTableTag = false;
                mtm.addMasterTable(mt);
            } else if (qName.equalsIgnoreCase("displayname")) {
                displayNameTag = false;
            } else if (qName.equalsIgnoreCase("tablename")) {
                tableNameTag = false;
            } else if (qName.equalsIgnoreCase("columns")) {
                insideColumnsTag = false;
            } else if (qName.equalsIgnoreCase("column")) {
                insideColumnTag = false;
                uniqueColumn = false;
            } else if (qName.equalsIgnoreCase("cabinet")) {
                insideCabinetTag = false;
            } else if (qName.equalsIgnoreCase("id")) {
                idTag = false;
            } else if (qName.equalsIgnoreCase("permittedgroup")) {
                insidePermittedgroupTag = false;
            }
        }

        @Override
        public void characters(char ch[], int start, int length)
                throws SAXException {
            if (displayNameTag) {
                mt.setDisplayName(new String(ch, start, length));
            } else if (tableNameTag) {
                mt.setTableName(new String(ch, start, length));
            } else if (insideColumnTag) {
                String value = new String(ch, start, length);
                mt.addColumns(new String(ch, start, length));
                if (uniqueColumn) {
                    mt.addUniqueColumns(value);
                }
            } else if (insideCabinetTag) {
                mt.setCabinet(new String(ch, start, length));
            } else if (idTag) {
                mt.setId(new String(ch, start, length));
            } else if (insidePermittedgroupTag) {
                mt.setPermittedGroup(new String(ch, start, length));
            }
        }
    }

    public MasterTableManager getMasterTableManager(String path) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        SettingsHandler handler = new SettingsHandler();
        saxParser.parse(path, handler);

        return handler.getMasterTableManager();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new SettingsReader().getMasterTableManager("settings.xml").getMasterTable("1"));
    }
}
