using System.Text;
using System.Security.Cryptography;

/**
 * AES Rijndael encryption.
 * Author: Pantelis Andrianakis
 * Date: December 23rd 2017
 */
class Encryption
{
    // Secret keyword.
    static readonly string PASSWORD = "SECRET_KEYWORD";
    // 16-byte private password.
    static readonly byte[] IV = Encoding.UTF8.GetBytes("0123456789012345");

    static readonly byte[] key = new MD5CryptoServiceProvider().ComputeHash(Encoding.UTF8.GetBytes(PASSWORD));
    static readonly RijndaelManaged cipher = new RijndaelManaged();

    public static byte[] Encrypt(byte[] bytes)
    {
        return cipher.CreateEncryptor(key, IV).TransformFinalBlock(bytes, 0, bytes.Length);
    }

    public static byte[] Decrypt(byte[] bytes)
    {
        return cipher.CreateDecryptor(key, IV).TransformFinalBlock(bytes, 0, bytes.Length);
    }
}