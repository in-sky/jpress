/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.core.template;

import com.jfinal.kit.PathKit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TemplateManager {

    private static final TemplateManager me = new TemplateManager();

    private TemplateManager() {

    }

    public static TemplateManager me() {
        return me;
    }


    public List<Template> getInstalledTemplates() {

        String basePath = PathKit.getWebRootPath() + "/templates";

        List<File> templateFolderList = new ArrayList<File>();
        scanTemplateFloders(new File(basePath), templateFolderList);

        List<Template> templatelist = null;
        if (templateFolderList.size() > 0) {
            templatelist = new ArrayList<Template>();
            for (File templateFolder : templateFolderList) {
                templatelist.add(new Template(templateFolder + "/template.properties"));
            }
        }
        return templatelist;
    }


    private void scanTemplateFloders(File file, List<File> list) {
        if (file.isDirectory()) {

            File configFile = new File(file, "template.properties");

            if (configFile.exists() && configFile.isFile()) {
                list.add(file);
            } else {
                File[] files = file.listFiles();
                if (null != files && files.length > 0) {
                    for (File f : files) {
                        if (f.isDirectory())
                            scanTemplateFloders(f, list);
                    }
                }
            }
        }
    }

}