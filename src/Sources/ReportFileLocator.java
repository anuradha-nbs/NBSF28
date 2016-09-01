/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

import com.nbs.view.common.GeneralUserLogin;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

/**
 *
 * @author mmh
 */
public class ReportFileLocator {
    private static String NETWORK_FOLDER = "";
    public static SmbFileInputStream locateReportFile(String fileName){
        SmbFileInputStream sfis = null;
        try {
            NETWORK_FOLDER = GeneralUserLogin.data.getReportPath()+fileName;
            System.out.println(NETWORK_FOLDER);
            SmbFile smbFile = new  SmbFile(NETWORK_FOLDER);
            sfis = new SmbFileInputStream(smbFile);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReportFileLocator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmbException ex) {
            Logger.getLogger(ReportFileLocator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ReportFileLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sfis;
    }
}
