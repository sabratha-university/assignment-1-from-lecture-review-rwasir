public class SimpleLocation {
    // الخصائص (Attributes)
    private double latitude;   // خط العرض
    private double longitude;  // خط الطول
    //  (Constructor)
    public SimpleLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    //  دوالgetter للوصول إلى القيم
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    /*(Overriding)
     إعادة تعريف دالة toString() من الكلاس الأب Object */    
    @Override
    public String toString() {
        return String.format("Location(lat=%.6f, lon=%.6f)", latitude, longitude);
    }

    /*(Overloading) - الدالة الأولى
     تحسب المسافة بين موقعين من نوع  */
    public static double haversineDistanceKm(SimpleLocation a, SimpleLocation b) {
        final double R = 6371.0; // نصف قطر الأرض بالكيلومتر
        // تحويل القيم  
        double lat1 = Math.toRadians(a.getLatitude());
        double lon1 = Math.toRadians(a.getLongitude());
        double lat2 = Math.toRadians(b.getLatitude());
        double lon2 = Math.toRadians(b.getLongitude());
        // الفرق
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;
        // تطبيق معادلة Haversine
        double sinDLat = Math.sin(dLat / 2);
        double sinDLon = Math.sin(dLon / 2);
        double aa = sinDLat * sinDLat + Math.cos(lat1) * Math.cos(lat2) * sinDLon * sinDLon;
        double c = 2 * Math.atan2(Math.sqrt(aa), Math.sqrt(1 - aa));
        // النتيجة النهائية
        return R * c;
    }
    /* (Overloading) - الدالة الثانية
     نفس الاسم (haversineDistanceKm) ولكن معاملات مختلفة
     تستقبل الإحداثيات مباشرة بدلاً من كائنات*/
    public static double haversineDistanceKm(double lat1, double lon1, double lat2, double lon2) {
        SimpleLocation a = new SimpleLocation(lat1, lon1);
        SimpleLocation b = new SimpleLocation(lat2, lon2);
        return haversineDistanceKm(a, b); // استدعاء للدالة الأولى
    }
         // الدالة الرئيسية (Main)
    public static void main(String[] args) {
        //  موقعك الحالي جامعة صبراتة
        SimpleLocation myLocation = new SimpleLocation(32.79813, 12.49111);
        //  موقع كلية تقنية المعلومات الجميل
        SimpleLocation college = new SimpleLocation(32.8559851, 12.0575469);
        // حساب المسافة بين الموقعين باستخدام Overloading الأول
        double distance1 = haversineDistanceKm(myLocation, college);
        // حساب المسافة باستخدام Overloading الثاني
        double distance2 = haversineDistanceKm(
            myLocation.getLatitude(), myLocation.getLongitude(),
            college.getLatitude(), college.getLongitude()
        );
        // عرض النتائج
        System.out.println("  My current location: " + myLocation);
        System.out.println("  Al Jameel College of Information Technology : " + college);
        System.out.printf("The distance between the two locations  : %.2f كم%n", distance1);
        System.out.printf("Distance (Using Overloading The second): %.2f كم%n", distance2);
    }
}



