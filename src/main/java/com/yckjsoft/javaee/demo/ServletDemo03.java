package com.yckjsoft.javaee.demo;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by yong on 2016/10/8 0008.
 * 设置content-type响应头，指定回送数据类型
 */
public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 浏览器能接收(Accept)的数据类型有:
         'hqx' => 'application/mac-binhex40',
         'cpt' => 'application/mac-compactpro',
         'doc' => 'application/msword',
         'bin' => 'application/octet-stream',
         'dms' => 'application/octet-stream',
         'lha' => 'application/octet-stream',
         'lzh' => 'application/octet-stream',
         'exe' => 'application/octet-stream',
         'class' => 'application/octet-stream',
         'so' => 'application/octet-stream',
         'dll' => 'application/octet-stream',
         'oda' => 'application/oda',
         'pdf' => 'application/pdf',
         'ai' => 'application/postscript',
         'eps' => 'application/postscript',
         'ps' => 'application/postscript',
         'smi' => 'application/smil',
         'smil' => 'application/smil',
         'mif' => 'application/vnd.mif',
         'xls' => 'application/vnd.ms-excel',
         'ppt' => 'application/vnd.ms-powerpoint',
         'wbxml' => 'application/vnd.wap.wbxml',
         'wmlc' => 'application/vnd.wap.wmlc',
         'wmlsc' => 'application/vnd.wap.wmlscriptc',
         'bcpio' => 'application/x-bcpio',
         'vcd' => 'application/x-cdlink',
         'pgn' => 'application/x-chess-pgn',
         'cpio' => 'application/x-cpio',
         'csh' => 'application/x-csh',
         'dcr' => 'application/x-director',
         'dir' => 'application/x-director',
         'dxr' => 'application/x-director',
         'dvi' => 'application/x-dvi',
         'spl' => 'application/x-futuresplash',
         'gtar' => 'application/x-gtar',
         'hdf' => 'application/x-hdf',
         'js' => 'application/x-javascript',
         'skp' => 'application/x-koan',
         'skd' => 'application/x-koan',
         'skt' => 'application/x-koan',
         'skm' => 'application/x-koan',
         'latex' => 'application/x-latex',
         'nc' => 'application/x-netcdf',
         'cdf' => 'application/x-netcdf',
         'sh' => 'application/x-sh',
         'shar' => 'application/x-shar',
         'swf' => 'application/x-shockwave-flash',
         'sit' => 'application/x-stuffit',
         'sv4cpio' => 'application/x-sv4cpio',
         'sv4crc' => 'application/x-sv4crc',
         'tar' => 'application/x-tar',
         'tcl' => 'application/x-tcl',
         'tex' => 'application/x-tex',
         'texinfo' => 'application/x-texinfo',
         'texi' => 'application/x-texinfo',
         't' => 'application/x-troff',
         'tr' => 'application/x-troff',
         'roff' => 'application/x-troff',
         'man' => 'application/x-troff-man',
         'me' => 'application/x-troff-me',
         'ms' => 'application/x-troff-ms',
         'ustar' => 'application/x-ustar',
         'src' => 'application/x-wais-source',
         'xhtml' => 'application/xhtml+xml',
         'xht' => 'application/xhtml+xml',
         'zip' => 'application/zip',
         'au' => 'audio/basic',
         'snd' => 'audio/basic',
         'mid' => 'audio/midi',
         'midi' => 'audio/midi',
         'kar' => 'audio/midi',
         'mpga' => 'audio/mpeg',
         'mp2' => 'audio/mpeg',
         'mp3' => 'audio/mpeg',
         'aif' => 'audio/x-aiff',
         'aiff' => 'audio/x-aiff',
         'aifc' => 'audio/x-aiff',
         'm3u' => 'audio/x-mpegurl',
         'ram' => 'audio/x-pn-realaudio',
         'rm' => 'audio/x-pn-realaudio',
         'rpm' => 'audio/x-pn-realaudio-plugin',
         'ra' => 'audio/x-realaudio',
         'wav' => 'audio/x-wav',
         'pdb' => 'chemical/x-pdb',
         'xyz' => 'chemical/x-xyz',
         'bmp' => 'image/bmp',
         'gif' => 'image/gif',
         'ief' => 'image/ief',
         'jpeg' => 'image/jpeg',
         'jpg' => 'image/jpeg',
         'jpe' => 'image/jpeg',
         'png' => 'image/png',
         'tiff' => 'image/tiff',
         'tif' => 'image/tiff',
         'djvu' => 'image/vnd.djvu',
         'djv' => 'image/vnd.djvu',
         'wbmp' => 'image/vnd.wap.wbmp',
         'ras' => 'image/x-cmu-raster',
         'pnm' => 'image/x-portable-anymap',
         'pbm' => 'image/x-portable-bitmap',
         'pgm' => 'image/x-portable-graymap',
         'ppm' => 'image/x-portable-pixmap',
         'rgb' => 'image/x-rgb',
         'xbm' => 'image/x-xbitmap',
         'xpm' => 'image/x-xpixmap',
         'xwd' => 'image/x-xwindowdump',
         'igs' => 'model/iges',
         'iges' => 'model/iges',
         'msh' => 'model/mesh',
         'mesh' => 'model/mesh',
         'silo' => 'model/mesh',
         'wrl' => 'model/vrml',
         'vrml' => 'model/vrml',
         'css' => 'text/css',
         'html' => 'text/html',
         'htm' => 'text/html',
         'asc' => 'text/plain',
         'txt' => 'text/plain',
         'rtx' => 'text/richtext',
         'rtf' => 'text/rtf',
         'sgml' => 'text/sgml',
         'sgm' => 'text/sgml',
         'tsv' => 'text/tab-separated-values',
         'wml' => 'text/vnd.wap.wml',
         'wmls' => 'text/vnd.wap.wmlscript',
         'etx' => 'text/x-setext',
         'xsl' => 'text/xml',
         'xml' => 'text/xml',
         'mpeg' => 'video/mpeg',
         'mpg' => 'video/mpeg',
         'mpe' => 'video/mpeg',
         'qt' => 'video/quicktime',
         'mov' => 'video/quicktime',
         'mxu' => 'video/vnd.mpegurl',
         'avi' => 'video/x-msvideo',
         'movie' => 'video/x-sgi-movie',
         'ice' => 'x-conference/x-cooltalk',
         */
        //使用content-type响应头指定发送给浏览器的数据类型为"image/jpeg"
        resp.setHeader("content-type", "image/jpeg");
        //读取位于项目根webapp目录下的images文件夹里面的cat.jpg这张图片，返回一个输入流
        InputStream in = this.getServletContext().getResourceAsStream("images/cat.jpg");

        byte[] buffer = new byte[1024];
        int len = 0;

        //得到输出流
        OutputStream out = resp.getOutputStream();
        //读取输入流里面的内容存储到缓存区 buffer
        while ((len = in.read(buffer)) > 0) {
            //将缓存区里面的内容输出到浏览器中
            out.write(buffer, 0, len);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
