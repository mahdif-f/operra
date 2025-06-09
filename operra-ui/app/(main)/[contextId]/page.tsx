'use client';

import { useParams } from 'next/navigation';
import DynamicCrudForm from '@/app/components/DynamicCrudForm';

export default function DynamicPage() {
    const { contextId } = useParams() as { contextId: string };

    return (
        <div className="card">
            <h2>فرم: {contextId}</h2>
            <DynamicCrudForm contextId={contextId} />
        </div>
    );
}
